package net.lmor.botanicalextramachinery.config;

import io.github.noeppi_noeppi.libx.annotation.config.RegisterConfig;
import io.github.noeppi_noeppi.libx.config.Config;
import io.github.noeppi_noeppi.libx.config.Group;

@RegisterConfig(value = "client", client = true)
public class LibXClientConfig {
    @Config({"Whether to show mana/water as a number when hovering over its capacity?"})
    public static boolean numericalFluid = true;
    @Config({"Format number using suffixes (K, M, etc.)? If false, then the full number will be shown."})
    public static boolean formattedNumberSuffix = false;

    @Config({"Show information about additional slots? For example, the slot where the catalyst is placed in the mana pool."})
    public static boolean slotInfo = true;

    @Group({"Mechanism rendering settings."})
    public static class RenderingVisualContent {
        @Config({"If you disable this setting, custom rendering will be disabled for all machines and will ignore other configuration settings.."})
        public static boolean all = true;

        @Group({"Mana Pool Rendering Settings."})
        public static class ManaPoolSettings {
            @Config
            public static boolean manaPoolBase = true;
            @Config
            public static boolean manaPoolUpgraded = true;
            @Config
            public static boolean manaPoolAdvanced = true;
            @Config
            public static boolean manaPoolUltimate = true;

            public ManaPoolSettings(){}
        }

        @Group({"Runic Altars Rendering Settings."})
        public static class RunicAltarSettings {
            @Config
            public static boolean runicAltarBase = true;
            @Config
            public static boolean runicAltarUpgraded = true;
            @Config
            public static boolean runicAltarAdvanced = true;
            @Config
            public static boolean runicAltarUltimate = true;

            public RunicAltarSettings(){}
        }

        @Group({"Daisy Rendering Settings."})
        public static class DaisySettings {
            @Config
            public static boolean daisyBase = true;
            @Config
            public static boolean daisyUpgraded = true;
            @Config
            public static boolean daisyAdvanced = true;
            @Config
            public static boolean daisyUltimate = true;

            public DaisySettings(){}
        }

        @Group({"Apothecary rendering settings."})
        public static class ApothecarySettings {
            @Config
            public static boolean apothecaryBase = true;
            @Config
            public static boolean apothecaryUpgraded = true;
            @Config
            public static boolean apothecaryAdvanced = true;
            @Config
            public static boolean apothecaryUltimate = true;

            public ApothecarySettings(){}
        }

        @Group({"Alfheim Market rendering settings."})
        public static class AlfheimMarketSettings {
            @Config
            public static boolean alfheimMarketBase = true;
            @Config
            public static boolean alfheimMarketUpgraded = true;
            @Config
            public static boolean alfheimMarketAdvanced = true;
            @Config
            public static boolean alfheimMarketUltimate = true;

            public AlfheimMarketSettings(){}
        }

        public RenderingVisualContent() {
        }
    }

    public LibXClientConfig() {
    }
}
