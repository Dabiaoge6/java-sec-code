package org.vulhunter.vulnerfix.untrustdeserial;

import org.vulhunter.common.untrustdeserialization.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public final class UserObjectInputStream extends ObjectInputStream {

	public UserObjectInputStream() throws IOException, SecurityException {
		super();
	}

	public UserObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	protected Class<?> resolveClass(ObjectStreamClass desc)
            throws IOException, ClassNotFoundException {
        if (!desc.getName().equals(User.class.getName())) {
            throw new ClassNotFoundException(desc.getName() + " forbidden!");
        }
        return super.resolveClass(desc);
    }
}
