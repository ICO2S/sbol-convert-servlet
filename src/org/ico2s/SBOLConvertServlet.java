package org.ico2s;

import org.sbolstandard.core2.SBOLConversionException;
import org.sbolstandard.core2.SBOLDocument;
import org.sbolstandard.core2.SBOLReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SBOLConvertServlet extends HttpServlet
{
    public SBOLConvertServlet()
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        SBOLDocument document = null;

        try
        {
            document = SBOLReader.read(request.getInputStream());
        }
        catch(Exception e)
        {
            response.setContentType("text/plain");
            response.sendError(500, e.toString());

            return;
        }

        response.setContentType("application/rdf+xml");

        try {

            document.write(response.getOutputStream());

        } catch (SBOLConversionException e) {

            response.setContentType("text/plain");
            response.sendError(500, e.toString());

        }


    }
}
