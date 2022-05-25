package com.se.sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avatar {

    public enum AVATAR_STYLE {Circle, Transparent}

    public enum TOP {
        NoHair,
        Eyepatch,
        Hat,
        Hijab,
        Turban,
        WinterHat1,
        WinterHat2,
        WinterHat3,
        WinterHat4,
        LongHairBigHair,
        LongHairBob,
        LongHairBun,
        LongHairCurly,
        LongHairCurvy,
        LongHairDreads,
        LongHairFrida,
        LongHairFro,
        LongHairFroBand,
        LongHairNotTooLong,
        LongHairShavedSides,
        LongHairMiaWallace,
        LongHairStraight,
        LongHairStraight2,
        LongHairStraightStrand,
        ShortHairDreads01,
        ShortHairDreads02,
        ShortHairFrizzle,
        ShortHairShaggyMullet,
        ShortHairShortCurly,
        ShortHairShortFlat,
        ShortHairShortRound,
        ShortHairShortWaved,
        ShortHairSides,
        ShortHairTheCaesar,
        ShortHairTheCaesarSidePart
    }

    public enum ACCESSORIES {
        Blank,
        Kurt,
        Prescription01,
        Prescription02,
        Round,
        Sunglasses,
        Wayfarers
    }

    public enum HAIRCOLOR {
        Auburn,
        Black,
        Blonde,
        BlondeGolden,
        Brown,
        BrownDark,
        PastelPink,
        Platinum,
        Red,
        SilverGray
    }

    public enum FACIALHAIR {
        Blank,
        BeardMedium,
        BeardLight,
        BeardMagestic,
        MoustacheFancy,
        MoustacheMagnum
    }

    public enum CLOTHE {
        BlazerShirt,
        BlazerSweater,
        CollarSweater,
        GraphicShirt,
        Hoodie,
        Overall,
        ShirtCrewNeck,
        ShirtScoopNeck,
        ShirtVNeck
    }

    public enum CLOTHECOLOR {
        Black,
        Blue01,
        Blue02,
        Blue03,
        Gray01,
        Gray02,
        Heather,
        PastelBlue,
        PastelGreen,
        PastelOrange,
        PastelRed,
        PastelYellow,
        Pink,
        Red,
        White
    }

    public enum EYE {
        Close,
        Cry,
        Default,
        Dizzy,
        EyeRoll,
        Happy,
        Hearts,
        Side,
        Squint,
        Surprised,
        Wink,
        WinkWacky
    }

    public enum EYEBROW {
        Angry,
        AngryNatural,
        Default,
        DefaultNatural,
        FlatNatural,
        RaisedExcited,
        RaisedExcitedNatural,
        SadConcerned,
        SadConcernedNatural,
        UnibrowNatural,
        UpDown,
        UpDownNatural
    }

    public enum MOUTH {
        Concerned,
        Default,
        Disbelief,
        Eating,
        Grimace,
        Sad,
        ScreamOpen,
        Serious,
        Smile,
        Tongue,
        Twinkle,
        Vomit
    }

    public enum SKINCOLOR {
        Tanned,
        Yellow,
        Pale,
        Light,
        Brown,
        DarkBrown,
        Black
    }

    AVATAR_STYLE avatarStyle;
    TOP top;
    ACCESSORIES accessories;
    HAIRCOLOR hairColor;
    FACIALHAIR facialHair;
    CLOTHE clothe;
    CLOTHECOLOR clotheColor;
    EYE eye;
    EYEBROW eyebrow;
    MOUTH mouth;
    SKINCOLOR skinColor;

    public static Avatar getDefault() {
        Avatar avatar = Avatar.builder().build();
        avatar.setAvatarStyle(AVATAR_STYLE.Circle);
        avatar.setTop(TOP.Hat);
        avatar.setAccessories(ACCESSORIES.Wayfarers);
        avatar.setFacialHair(FACIALHAIR.Blank);
        avatar.setClothe(CLOTHE.BlazerShirt);
        avatar.setEye(EYE.Default);
        avatar.setEyebrow(EYEBROW.Default);
        avatar.setMouth(MOUTH.Default);
        avatar.setSkinColor(SKINCOLOR.Light);

        return avatar;
    }
}

