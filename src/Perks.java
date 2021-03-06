
public class Perks {
    private long helium;
    private int[] perks;

    public Perks(final int[] perks) {
        this.perks = new int[Perk.values().length];
        for (int x = 0; x < Perk.values().length; x++) {
            this.perks[x] = perks[x];
        }
    }
    
    public Perks(final Perks perks){
        this.helium = perks.helium;
        this.perks = new int[Perk.values().length];
        for (int x = 0; x < Perk.values().length; x++) {
            this.perks[x] = perks.perks[x];
        }
    }
    
    private long perkCost(final Perk perk, final int amount){
        if (perk.additive){
            long base = perk.baseCost+((long)perk.scaleFactor*perks[perk.ordinal()]);
            return ((base+(long)(perk.scaleFactor*(amount-1)/2))*amount);
        }
        else{
            long total = 0;
            int level = perks[perk.ordinal()];
            for (int x = 1; x<=amount;x++){
                total += perk.baseCost*Math.pow(perk.scaleFactor, level);
                level++;
            }
            return total;
        }
    }
    
    public boolean buyPerk(final Perk perk, final int amount){
        long cost = perkCost(perk,amount);
        if (cost<=helium){
            helium-=cost;
            perks[perk.ordinal()]+=amount;
            return true;
        }
        return false;
    }
    
    public int getLevel(final Perk perk){
        return perks[perk.ordinal()];
    }
}
