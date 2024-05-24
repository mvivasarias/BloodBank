<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" /> 
    <xsl:template match="/">// takes the whole data
        <html> //html, head, title, body--> standard HTML elements used to structure the HTML output.
        <head>
            <title>Donations</title>
        </head>
        <body>
            <h2>Donations</h2> // heading element h2that displays "Donations"
            <table border="1">
                <tr> //table row
                    <th>Amount</th> //table header
                    <th>Date</th>
                    <th>Donor</th>
                    <th>Donor DOB</th>
                    <th>Blood Type</th>
                    <th>Nurse</th>
                    <th>Nurse Email</th>
                    <th>Nurse Salary</th>
                    <th>Nurse Hours</th>
                </tr> //end table row 
                <xsl:for-each select="donations/donation"> //iterates over each donation within the donation
                    <tr>
                        <td><xsl:value-of select="@amount" /></td> //table data elements value-of retrives value of amount in donations
                        <td><xsl:value-of select="date" /></td>
                        <td><xsl:value-of select="donor/@name" /> <xsl:value-of select="donor/@surname" /></td> //donor name and surname attributes @
                        <td><xsl:value-of select="donor/dob" /></td>
                        <td><xsl:value-of select="donor/BloodType[1]" /></td>
                        <td><xsl:value-of select="nurse/@name" /> <xsl:value-of select="nurse/@surname" /></td>
                        <td><xsl:value-of select="nurse/email" /></td>
                        <td><xsl:value-of select="nurse/contract/salary" /></td>
                        <td><xsl:value-of select="nurse/contract/hours" /></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>