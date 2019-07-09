package org.jinspect.core.common;

import java.io.File;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SigarContextBuilder {

	private static Logger logger = LoggerFactory.getLogger(SigarContextBuilder.class);

	public static Sigar getSigar(String sigarLibDirPath) {
		try {
			File sigarLibDir = new File(sigarLibDirPath);
			String path = System.getProperty("java.library.path");
			String sigarPath = sigarLibDir.getCanonicalPath();
			if (!path.contains(sigarPath)) {
				path += (isOSWin() ? ";" : ":") + sigarPath;
				System.setProperty("java.library.path", path);
			}
			return new Sigar();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	private static boolean isOSWin() {// OS °æ±¾ÅĞ¶Ï
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("win") >= 0) {
			return true;
		} else {
			return false;
		}
	}
}
