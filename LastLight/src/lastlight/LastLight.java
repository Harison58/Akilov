package lastlight;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import mindustry.type.Planet;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.SunMesh;
import lastlight.content.LastLightGenerator;
import mindustry.mod.Mod;

public class LastLight extends Mod {

    public static Planet sun, mars, jupiter, saturn, uranus, neptune, titan, mintaka, mintakab, quasar;

    @Override
    public void loadContent(){

        sun = new Planet("ll-sun", null, 9f) {{
            localizedName = "Солнце-гигант";
            bloom = true;
            accessible = false;
            meshLoader = () -> new SunMesh(
                this, 6,
                5, 0.3, 1.7, 1.2, 1,
                1.1f,
                Color.valueOf("8a1c1c"),
                Color.valueOf("b3311a"),
                Color.valueOf("e0491f"),
                Color.valueOf("e0491f"),
                Color.valueOf("ff7a3d"),
                Color.valueOf("ffb066")
            );
        }};

        // сдвигаем звезду (и вместе с ней всю систему) подальше от центра
        sun.position.set(600f, 0f, 0f);

        mars = new Planet("ll-mars", sun, 0.9f, 3) {{
            localizedName = "Марс";
            atmosphereColor = Color.valueOf("#c1440e");
            generator = new LastLightGenerator("#c1440e", "#8a3010", "#e8b090", 1f);
            meshLoader = () -> new HexMesh(this,6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 14f;
        }};

        jupiter = new Planet("ll-jupiter", sun, 1.1f, 6) {{
            localizedName = "Юпитер";
            atmosphereColor = Color.valueOf("#d8b384");
            generator = new LastLightGenerator("#d8b384", "#a87850", "#f0e0c0", 2f, 14f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 26f;
        }};

        saturn = new Planet("ll-saturn", sun, 1.1f, 6) {{
            localizedName = "Сатурн";
            atmosphereColor = Color.valueOf("#e3c89a");
            generator = new LastLightGenerator("#e3c89a", "#c9a876", "#f5e8c8", 3f, 12f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 48f;
        }};

        titan = new Planet("ll-titan", saturn, 0.9f, 2) {{
            localizedName = "Титан";
            atmosphereColor = Color.valueOf("#e8a33d");
            generator = new LastLightGenerator("#e8a33d", "#c07820", "#f5d090", 4f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 4f;
        }};

        uranus = new Planet("ll-uranus", sun, 1f, 4) {{
            localizedName = "Уран";
            atmosphereColor = Color.valueOf("#9fe3e8");
            generator = new LastLightGenerator("#9fe3e8", "#6bc0c8", "#c8f5f8", 5f, 6f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 96f;
        }};

        neptune = new Planet("ll-neptune", sun, 1f, 4) {{
            localizedName = "Нептун";
            atmosphereColor = Color.valueOf("#3f5ec2");
            generator = new LastLightGenerator("#3f5ec2", "#2a4090", "#7090e0", 6f, 8f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 0;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 150f;
        }};

        mintaka = new Planet("mintaka-A", null, 6f) {{
            localizedName = "Минтака";
            bloom = true;
            accessible = false;
            meshLoader = () -> new SunMesh(
                this, 6,
                7, 0.3, 1.7, 1.2, 1,
                1.1f,
                Color.valueOf("#a0ffff"),
                Color.valueOf("#a0ffff"),
                Color.valueOf("#a0ffff"),
                Color.valueOf("#ff0000"),
                Color.valueOf("#a0ffff"),
                Color.valueOf("#a0ffff")
            );
        }};
        
        mintaka.position.set(50f, 0f, 0f);

        mintakab =  new Planet("mintaka-b", mintaka, 1f, 3) {{
            localizedName = "Аид";
            atmosphereColor = Color.valueOf("#9fe3e8");
            generator = new LastLightGenerator("#9fe3e8", "#6bc0c8", "#c8f5f8", 5f, 6f);
            meshLoader = () -> new HexMesh(this, 6);
            allowLaunchToNumbered = true;
            startSector = 1;
            accessible = true;
            alwaysUnlocked = true;
            orbitRadius = 96f;
        }};

        quasar = new Planet("ll-quasar", sun, 0.4f) {{
            localizedName = "Квазар";
            accessible = true;
            bloom = true;
            lightColor = Color.valueOf("#aaddff");
            orbitRadius = 12f;   // достаточно далеко, но в рамках проверенного диапазона рендера
            orbitTime = 20000f;  // очень медленное движение — квазар почти неподвижен на фоне
            meshLoader = () -> new SunMesh(
                this, 4,
                6, 0.25, 2.2, 1.4, 1,
                2.5f,                        // выше скорость "кипения" — более активный, турбулентный вид
                Color.valueOf("#1a1a4d"),     // тёмное ядро — почти чёрно-синее
                Color.valueOf("#3355aa"),
                Color.valueOf("#5588dd"),
                Color.valueOf("#5588dd"),
                Color.valueOf("#aaddff"),
                Color.valueOf("#ffffff")      // ослепительно белый край
            );
        }};
    }
}