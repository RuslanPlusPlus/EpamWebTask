package by.ruslan.web.command;

public enum CommandType {
    CREATE_USER_LIST{
        {
            this.command = new CreateCommand();
        }
    },
    DISPLAY_USER_LIST{
        {
            this.command = new DisplayCommand();
        }
    },
    SORT_USER_LIST{
        {
            this.command = new SortCommand();
        }
    },
    DISPLAY_BY_AGE_RANGE{
        {
            this.command = new AgeRangeCommand();
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
