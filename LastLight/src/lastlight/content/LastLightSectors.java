package lastlight.content;

import mindustry.type.SectorPreset;
import lastlight.LastLight;

public class LastLightSectors {
    public static SectorPreset marsStart;

    public static void load(){
        marsStart = new SectorPreset("mars-start", "mars-start", LastLight.mars, 0) {{
            addStartingItems = true;
            requireUnlock = false; // сразу доступен, без предварительной разблокировки
        }};
    }
}