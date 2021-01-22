<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:template match="/">
        <html>
            <head>
                <title> Login Page</title>

            </head>
            <body>




                <div style="text-align: center;  padding: 70px 0;">
                    <form action="./loginServlet" method="post"> <!-- action must be changed to reference a file -->
                        <h2>Enter Your Login Details Below:</h2>
                        <table border="1" style="margin: auto; border:solid; border-collapse: collapse; ">

                            <tr >
                                <td style="background-color:LightSteelBlue;">
                                    <b> <xsl:value-of select="//userid"/></b>
                                </td>
                                <th>
                                    <input type="text" name="userid" />
                                </th>
                            </tr>
                            <tr>
                                <td style="background-color:LightSteelBlue;">
                                    <b><xsl:value-of select="//password"/></b>
                                </td>
                                <th>
                                    <input type="password" name="password" />
                                </th>
                            </tr>
                        </table>

                        <input  style="font-size:20px;" type="submit" name="loginbtn" value="Log In"/>
                    </form>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>