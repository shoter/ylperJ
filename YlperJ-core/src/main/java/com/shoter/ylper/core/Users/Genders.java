package com.shoter.ylper.core.Users;

public enum Genders {
    Male((byte)1),
    Female((byte)2),
    Other((byte)3);

    private final byte id;
    Genders(byte id)
    {
        this.id = id;
    }

    public byte getValue()
    {
        return this.id;
    }

    public static boolean isCorrectGender(byte genderId)
    {
        for(Genders g : Genders.values())
        {
            if(g.id == genderId)
                return true;
        }

        return false;
    }

}
