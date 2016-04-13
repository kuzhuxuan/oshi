/**
 * Oshi (https://github.com/dblock/oshi)
 *
 * Copyright (c) 2010 - 2016 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * dblock[at]dblock[dot]org
 * alessandro[at]perucchi[dot]org
 * widdis[at]gmail[dot]com
 * enrico[dot]bianchi[at]gmail[dot]com
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.jna.platform.linux;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

/**
 * Linux C Library. This class should be considered non-API as it may be removed
 * if/when its code is incorporated into the JNA project.
 * 
 * @author widdis[at]gmail[dot]com
 */
public interface Libc extends Library {

    static final Libc INSTANCE = (Libc) Native.loadLibrary("c", Libc.class);

    static final class Sysinfo extends Structure {
        public NativeLong uptime; // Seconds since boot
        // 1, 5, and 15 minute load averages

        public NativeLong[] loads = new NativeLong[3];

        public NativeLong totalram; // Total usable main memory size

        public NativeLong freeram; // Available memory size

        public NativeLong sharedram; // Amount of shared memory

        public NativeLong bufferram; // Memory used by buffers

        public NativeLong totalswap; // Total swap space size

        public NativeLong freeswap; // swap space still available

        public short procs; // Number of current processes

        public NativeLong totalhigh; // Total high memory size

        public NativeLong freehigh; // Available high memory size

        public int mem_unit; // Memory unit size in bytes

        public byte[] _f = new byte[8]; // Won't be written for 64-bit systems

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[] { "uptime", "loads", "totalram", "freeram", "sharedram", "bufferram",
                    "totalswap", "freeswap", "procs", "totalhigh", "freehigh", "mem_unit", "_f" });
        }
    }

    int sysinfo(Sysinfo info);
    
    // Native call for getting load average
    int getloadavg(double[] loadavg, int nelem);
}
