package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Results.MethodResult;

public interface DemandService {
    MethodResult canAdd(Demand demand);
    void add(Demand demand);

    MethodResult canRemove(long id);
    void Remove(long id);
}
