package com.shoter.ylper.core.Demands;

import com.shoter.ylper.core.Results.MethodResult;

import java.util.List;

public interface DemandService {
    MethodResult canAdd(Demand demand);
    void add(Demand demand);

    MethodResult canRemove(long id);
    void remove(long id);

    Demand get(long demandId);
}
