package org.spring.demo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

public class TestServletUpload extends FileUploadBase {

  @Override
  public FileItemFactory getFileItemFactory() {
    return null;
  }

  @Override
  public void setFileItemFactory(FileItemFactory fileItemFactory) {
  }

  public List<FileItem> parseRequest(final HttpServletRequest request) throws FileUploadException {
    List<FileItem> list = (List<FileItem>) this
        .parseRequest((RequestContext) new ServletRequestContext(request));

    TestTracker.track("org.spring.demo.TestServletUpload",
        new String[]{"vulnerability-unsafeFile-FileUploadBase-1"}, this, new Object[]{list});

    return list;
  }


}
