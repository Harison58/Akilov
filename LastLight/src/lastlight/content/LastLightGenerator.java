package lastlight.content;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import arc.util.noise.Simplex;
import mindustry.content.Blocks;
import mindustry.game.Schematics;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;

import static mindustry.Vars.state;

public class LastLightGenerator extends PlanetGenerator {

    Color colorA, colorB, colorC;
    float seedOffset;
    boolean banded;
    float bandFrequency;

    // конструктор для пятнистых планет (Марс, Титан)
    public LastLightGenerator(String colorHexA, String colorHexB, String colorHexC, float seedOffset){
        this.colorA = Color.valueOf(colorHexA);
        this.colorB = Color.valueOf(colorHexB);
        this.colorC = Color.valueOf(colorHexC);
        this.seedOffset = seedOffset;
        this.banded = false;
    }

    // конструктор для полосатых планет (Юпитер, Сатурн, Уран, Нептун)
    public LastLightGenerator(String colorHexA, String colorHexB, String colorHexC, float seedOffset, float bandFrequency){
        this(colorHexA, colorHexB, colorHexC, seedOffset);
        this.banded = true;
        this.bandFrequency = bandFrequency;
    }

    @Override
    public boolean allowLanding(Sector sector){
        return true;
    }

    @Override
    public void getColor(Vec3 position, Color out){
        if(banded){
            // широта: -1 (южный полюс) .. 1 (северный полюс)
            float lat = position.y;

            // турбулентность полос через шум
            float turbulence = Simplex.noise3d(seed + (int)seedOffset, 4, 0.5f, 1f/4f, position.x * 6f, position.y * 2f, position.z * 6f) * 0.15f;

            float band = (float)Math.sin((lat + turbulence) * bandFrequency);
            float t = (band + 1f) / 2f; // приводим -1..1 к 0..1

            out.set(colorA).lerp(colorB, t);

            // редкие светлые завихрения (аналог Большого красного пятна)
            float spot = Simplex.noise3d(seed + (int)seedOffset + 10, 3, 0.5f, 1f/6f, position.x * 10f, position.y * 10f, position.z * 10f);
            if(spot > 0.72f){
                out.lerp(colorC, (spot - 0.72f) * 3f);
            }
        }else{
            float n1 = Simplex.noise3d(seed + (int)seedOffset, 4, 0.5f, 1f/3f, position.x * 4f, position.y * 4f, position.z * 4f);
            float n2 = Simplex.noise3d(seed + (int)seedOffset + 1, 3, 0.5f, 1f/5f, position.x * 8f, position.y * 8f, position.z * 8f);

            out.set(colorA).lerp(colorB, n1);
            if(n2 > 0.6f){
                out.lerp(colorC, (n2 - 0.6f) * 2f);
            }
        }
    }

    @Override
    protected void generate(){
        pass((x, y) -> {
            floor = Blocks.sand;
            block = Blocks.air;
        });
        Schematics.placeLaunchLoadout(width / 2, height / 2);
        state.rules.env = sector.planet.defaultEnv;
    }
}