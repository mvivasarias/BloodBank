<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Personal Details with Donations</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f0f0f0;
                        color: #333;
                    }
                    h2 {
                        color: #2e6da4;
                    }
                    table {
                        border-collapse: collapse;
                        width: 100%;
                        margin-bottom: 20px;
                    }
                    th, td {
                        border: 1px solid #ddd;
                        padding: 8px;
                    }
                    th {
                        background-color: #4CAF50;
                        color: white;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    tr:hover {
                        background-color: #ddd;
                    }
                </style>
            </head>
            <body>
                <h2>Personal Details with Donations</h2>
                <xsl:for-each select="Personal">
                    <h3>Personal Details</h3>
                    <table>
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
                    <table>
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
                    <br />
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
