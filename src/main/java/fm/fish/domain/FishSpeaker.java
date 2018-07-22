package fm.fish.domain;

import fm.fish.util.RandomUtil;

import java.util.Arrays;
import java.util.Random;

public class FishSpeaker {
    private static final Random R = new Random();

    public static String get() {

        String rFish = Fish.random().get();
        String rPhone = MEGAPHONE.random().get();
        return rPhone + rFish;
    }

    enum Fish {
        HEADGEHOG(new String(Character.toChars(0x1F421))),
        COLLOR(new String(Character.toChars(0x1F420))),
        SELEDKA(new String(Character.toChars(0x1F41F))),
        DOLPHIN(new String(Character.toChars(0x1F42C))),
        TAILY(new String(Character.toChars(0x1F40B))),
        WHALE(new String(Character.toChars(0x1F433)));

        private String s;

        Fish(String s) {
            this.s = s;
        }

        public static Fish random() {
            return Arrays.asList(values()).get(R.nextInt(values().length));
        }

        public String get() {
            return s;
        }
    }

    enum MEGAPHONE {
        GOLD(new String(Character.toChars(0x1F4E3))),
        SILVER(new String(Character.toChars(0x1F399))),
        BLACK(new String(Character.toChars(0x1F3A4)));

        private String s;

        MEGAPHONE(String s) {
            this.s = s;
        }

        public static MEGAPHONE random() {
            return RandomUtil.dice(Arrays.asList(values()));
        }

        public String get() {
            return s;
        }
    }
}
