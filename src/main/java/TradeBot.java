import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TradeBot extends ListenerAdapter {
    private static final long GUILD_ID = 1264052181888274604L;
    private static Connection connection;

    public static void main(String[] args) {
        // Load environment variables from .env
        Dotenv dotenv = Dotenv.configure().directory("./").load();

        String token = dotenv.get("DISCORD_BOT_TOKEN");
        String dbUrl = dotenv.get("DATABASE_URL");

        // Validate Bot Token
        if (token == null || token.isEmpty()) {
            System.err.println("❌ ERROR: DISCORD_BOT_TOKEN is not set! Check your .env file.");
            return;
        } else {
            System.out.println("✅ Bot token found: " + token.substring(0, 5) + "*****");
        }

        // Validate Database URL
        if (dbUrl == null || dbUrl.isEmpty()) {
            System.err.println("❌ ERROR: DATABASE_URL is not set! Check your .env file.");
            return;
        } else {
            System.out.println("✅ Database URL found.");
        }

        // Connect to PostgreSQL
        try {
            connection = DriverManager.getConnection(dbUrl);
            setupDatabase();
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            return;
        }

        // Initialize Discord Bot
        try {
            JDABuilder.createDefault(token)
                    .addEventListeners(new TradeBot())
                    .build();
        } catch (Exception e) {
            System.err.println("❌ Failed to start Discord bot: " + e.getMessage());
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("✅ Bot is online!");
        Guild guild = event.getJDA().getGuildById(GUILD_ID);
        if (guild != null) {
            guild.updateCommands().addCommands(
                    Commands.slash("ping", "Check bot status"),
                    Commands.slash("swing", "Start a new trade"),
                    Commands.slash("open_trades", "View current trades")
            ).queue();
            System.out.println("✅ Commands Registered!");
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping":
                event.reply("🏓 Pong! Bot is online ✅").setEphemeral(true).queue();
                break;
            case "swing":
                // Implement trade initiation
                event.reply("Trade setup coming soon!").setEphemeral(true).queue();
                break;
            case "open_trades":
                // Implement fetching trades from the database
                event.reply("Fetching open trades...").setEphemeral(true).queue();
                break;
        }
    }

    private static void setupDatabase() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS trades (" +
                    "id SERIAL PRIMARY KEY," +
                    "ticker VARCHAR(10) NOT NULL," +
                    "entry_range VARCHAR(20)," +
                    "tp VARCHAR(50)," +
                    "sl VARCHAR(20)," +
                    "break_price VARCHAR(20)," +
                    "expiry VARCHAR(20)," +
                    "direction VARCHAR(10)," +
                    "strategy VARCHAR(50)," +
                    "long_strike VARCHAR(20)," +
                    "short_strike VARCHAR(20))");
        }
    }
}
