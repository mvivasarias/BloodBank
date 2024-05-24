<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Personal Details with Donations</title>
            </head>
            <body>
                <h2>Personal Details with Donations</h2>
                <xsl:for-each select="Personal">
                    <h3>Personal Details</h3>
                    <table border="1">
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Email</th>
                            <th>Contract ID</th>
                            <th>Salary</th>
                            <th>Hours</th>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="@name" /></td>
                            <td><xsl:value-of select="@surname" /></td>
                            <td><xsl:value-of select="email" /></td>
                            <td><xsl:value-of select="contract/id" /></td>
                            <td><xsl:value-of select="contract/salary" /></td>
                            <td><xsl:value-of select="contract/hours" /></td>
                        </tr>
                    </table>
                    <h3>Donations</h3>
                    <table border="1">
                        <tr>
                            <th>Amount</th>
                            <th>Date</th>
                        </tr>
                        <xsl:for-each select="Donations/Donation">
                            <tr>
                                <td><xsl:value-of select="@amount" /></td>
                                <td><xsl:value-of select="date" /></td>
                            </tr>
                        </xsl:for-each>
                    </table>
                    <br /> //BREAK
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
