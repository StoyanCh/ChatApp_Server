package org.chat.app.database.sqlCommands;

import java.sql.Connection;

public class NewConvoSQLCommand
{
    private final Connection connection;
    public NewConvoSQLCommand(Connection connection) {
        this.connection = connection;
    }


}
