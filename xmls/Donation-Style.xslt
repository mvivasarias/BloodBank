<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" /> 
    <xsl:template match="/">
     <xsl:param name="bloodType" />
        <html>
        <head>
             <title>Donations </title>
            <style>
                body {
                    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
                    background-color: #f9f9f9;
                    color: #333;
                    padding: 20px;
                }
                h2 {
                    color: #4a90e2;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin: 20px 0;
                }
                th, td {
                    border: 1px solid #ddd;
                    padding: 8px;
                    text-align: left;
                }
                th {
                    background-color: #4a90e2;
                    color: white;
                }
                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }
                tr:hover {
                    background-color: #e9e9e9;
                }
                td {
                    color: #555;
                }
            </style>
        </head>
        <body>
            <h2>Donations - Blood Type: <xsl:value-of select="$bloodType" /></h2>
            <table border="1">
                <tr>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Donor</th>
                    <th>Donor DOB</th>
                    <th>Blood Type</th>
                    <th>Nurse</th>
                    <th>Nurse Email</th>
                    <th>Nurse Salary</th>
                    <th>Nurse Hours</th>
                </tr>
                <xsl:for-each select="donations/donation">
                    <tr>
                        <td><xsl:value-of select="@amount" /></td>
                        <td><xsl:value-of select="date" /></td>
                        <td><xsl:value-of select="donor/@name" /> <xsl:value-of select="donor/@surname" /></td>
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
