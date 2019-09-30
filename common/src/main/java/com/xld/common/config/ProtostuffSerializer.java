package com.xld.common.config;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * ProtoStuff序列化工具
 * @author xld
 */
public class ProtostuffSerializer<T> implements RedisSerializer<T> {

    private final Schema<ProtoWrapper> schema;
    private final ProtoWrapper wrapper;

    public ProtostuffSerializer() {
        this.wrapper = new ProtoWrapper();
        this.schema = RuntimeSchema.getSchema(ProtoWrapper.class);
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        if (t == null) {
            return new byte[0];
        }
        wrapper.data = t;
        try {
            return ProtostuffIOUtil.toByteArray(wrapper, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        ProtoWrapper newMessage = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, newMessage, schema);
        return (T) newMessage.data;
    }

    private static class ProtoWrapper {
        private Object data;
    }
}
