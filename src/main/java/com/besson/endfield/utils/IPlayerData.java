package com.besson.endfield.utils;

public interface IPlayerData {
    boolean hasReceivedProtocol();
    boolean hasReceivedOriginiumOre();
    boolean hasReceivedAmethystOre();
    boolean hasReceivedFerriumOre();
    void setHasReceivedProtocol(boolean value);
    void setHasReceivedOriginiumOre(boolean value);
    void setHasReceivedAmethystOre(boolean value);
    void setHasReceivedFerriumOre(boolean value);
}
