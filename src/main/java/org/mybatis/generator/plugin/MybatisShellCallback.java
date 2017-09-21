package org.mybatis.generator.plugin;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;

import java.io.File;
import java.util.StringTokenizer;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * @author qichunbo
 * @date 2017-07-07
 */
public class MybatisShellCallback implements ShellCallback {

    public MybatisShellCallback() {
        super();
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage) throws ShellException {
        String usrDir = System.getProperty("user.dir");
        String result = targetProject;
        if (null != usrDir && usrDir.trim().length() > 0) {
            result = usrDir + File.separator + targetProject;
        }

        File project = new File(result);
        if (!project.isDirectory()) {
            throw new ShellException(getString("Warning.9", //$NON-NLS-1$
                    targetProject));
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(getString("Warning.10", //$NON-NLS-1$
                        directory.getAbsolutePath()));
            }
        }

        return directory;
    }

    @Override
    public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags, String fileEncoding) throws ShellException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void refreshProject(String project) {
    }

    @Override
    public boolean isMergeSupported() {
        return false;
    }

    @Override
    public boolean isOverwriteEnabled() {
        return true;
    }
}
