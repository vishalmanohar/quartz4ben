package org.quartz4ben.converter;


import org.databene.commons.ConversionException;
import org.databene.commons.Converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class JobDataConverter implements Converter {
    @Override
    public Class getSourceType() {
        return String.class;
    }

    @Override
    public Class getTargetType() {
        return byte[].class;
    }

    @Override
    public byte[] convert(Object sourceValue) throws ConversionException {
        Map<String, String> jobDataMap  = new HashMap<String, String>();
        jobDataMap.put("JobID", String.valueOf(sourceValue));
        return convertToByteArray(jobDataMap);
    }

    private byte[] convertToByteArray(Object object) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o;
        try {
            o = new ObjectOutputStream(b);
            o.writeObject(object);
        } catch (IOException e) {
            throw new ConversionException(e);
        }
        return b.toByteArray();
    }

    @Override
    public boolean isParallelizable() {
        return false;
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }
}
