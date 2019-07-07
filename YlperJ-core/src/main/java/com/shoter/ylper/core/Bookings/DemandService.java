package com.shoter.ylper.core.Bookings;

import com.shoter.ylper.core.Results.MethodResult;

public interface DemandService {
    MethodResult canAdd(Demand demand);
    void Add(Demand demand);

    MethodResult canRemove(long id);
    void Remove(long id);
}
