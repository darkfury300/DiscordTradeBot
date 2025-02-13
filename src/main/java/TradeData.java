public class TradeData {
    private String ticker;
    private String entry;
    private String tp;
    private String sl;
    private String breakPrice;
    private String expiry;
    private String direction;
    private String strategy;
    private String longStrike;
    private String shortStrike;

    public TradeData(String ticker, String entry, String tp, String sl, String breakPrice, String expiry) {
        this.ticker = ticker;
        this.entry = entry;
        this.tp = tp;
        this.sl = sl;
        this.breakPrice = breakPrice;
        this.expiry = expiry;
    }

    public String getTicker() { return ticker; }
    public String getEntry() { return entry; }
    public String getTp() { return tp; }
    public String getSl() { return sl; }
    public String getBreakPrice() { return breakPrice; }
    public String getExpiry() { return expiry; }
    public String getDirection() { return direction; }
    public String getStrategy() { return strategy; }
    public String getLongStrike() { return longStrike; }
    public String getShortStrike() { return shortStrike; }

    public void setSl(String sl) { this.sl = sl; }
    public void setBreakPrice(String breakPrice) { this.breakPrice = breakPrice; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public void setDirection(String direction) { this.direction = direction; }
    public void setStrategy(String strategy) { this.strategy = strategy; }
    public void setLongStrike(String longStrike) { this.longStrike = longStrike; }
    public void setShortStrike(String shortStrike) { this.shortStrike = shortStrike; }
}
