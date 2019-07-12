package com.shoter.ylper.api.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {

    // not thread safe - the reason why it is not static
    protected SimpleDateFormat dateFormat;

    public Model()
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
    }

    public Date parseTime(String time) throws ParseException {
        return dateFormat.parse(time);
    }
}
