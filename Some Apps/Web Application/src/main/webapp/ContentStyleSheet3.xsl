<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>2020 Formula One World Championship</title>
                <xsl:text disable-output-escaping="yes">
			<![CDATA[
				<script type="text/javascript">

				window.onload = function(){
            	// stop keyboard event
           		 document.onkeydown = function (){
                var e = window.event || arguments[0];
                // stop F12
                if(e.keyCode == 123){
                    return false;
                //Ctrl+Shift+I
                }else if((e.ctrlKey) && (e.shiftKey) && (e.keyCode == 73)){
                    return false;
                //Shift+F10
                }else if((e.shiftKey) && (e.keyCode == 121)){
                    return false;
                //Ctrl+U
                }else if((e.ctrlKey) && (e.keyCode == 85)){
                    return false;
                }
            };
           		 //stop mouse right click
            	document.oncontextmenu = function (){
                return false;
            }

        }
                //stops the user from selecting text
                document.onselectstart = function(){
                    event.returnValue= false;
                }
                //stops the user from copying text
                document.oncopy = function() {
                    event.returnValue = false;
                }

            </script>
            ]]></xsl:text>



            </head>
            <xsl:comment> Prevents printing the page by instead showing a blank doc when the user tires to print</xsl:comment>
            <style>
                @media print {
                body{display:none}
                }
            </style>
            <body >
                <xsl:comment> creates a logout button at the top right corner</xsl:comment>
                <div style="text-align: right">
                    <form action = "LogOutPage" method ="post">
                        <input type="submit" value="Log Out"/>
                    </form>
                </div>
                <div style="text-align: center;  padding: 70px 0;">
                    <h1>BiblioPedia</h1>
                    <table style="margin: auto; border-collapse: collapse; ">
                        <tr >
                            <td style = "text-decoration : underline">
                                <b> <h3> <xsl:value-of select="//title"/> </h3> </b>
                            </td>
                        </tr>
                    </table>
                    <table >
                        <tr>
                            <td>
                                <xsl:value-of select="//text"/>
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <img height = "300" src="{/contentType/contents/photo/text()}" draggable = "false"/>
                            </th>
                        </tr>
                        <tr>
                            <td style = "color: #1965D3; font-family: Georgia">
                                <b><i> <xsl:value-of select="//quote"/> </i></b>
                            </td>
                        </tr>
                    </table>
                </div>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>