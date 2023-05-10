public class LoyaltyPointScheme {

    static int pointsGained;

    public LoyaltyPointScheme() {
        pointsGained = 0;
    }

    public static int getPointsGained() {
        return pointsGained;
    }

    public static void setPointsGained(int pointsGained) {
        LoyaltyPointScheme.pointsGained = pointsGained;
    }
}
