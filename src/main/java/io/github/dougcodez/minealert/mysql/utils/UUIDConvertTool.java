package io.github.dougcodez.minealert.mysql.utils;

import com.google.common.io.ByteStreams;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

@UtilityClass
public class UUIDConvertTool {

    public InputStream convertUniqueId(UUID uuid) {
        byte[] bytes = new byte[16];
        ByteBuffer.wrap(bytes)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return new ByteArrayInputStream(bytes);
    }

    public UUID convertBinaryStream(InputStream stream) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        try {
            buffer.put(ByteStreams.toByteArray(stream));
            buffer.flip();
            return new UUID(buffer.getLong(), buffer.getLong());
        } catch (IOException e) {
            // Handle the exception
        }
        return null;
    }
}