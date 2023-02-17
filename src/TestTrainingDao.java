import java.util.List;

public class TestTrainingDao {

    public static void main(String[] args) {

        TrainingDAO trainingDAO = new TrainingDAO();

        System.out.println(trainingDAO.getTraningSessionDataByTrainingId(1));
    }

    public static double getAverageMovementSpeed(List<TrainingSessionData> trainingSessionData){
        double avg = 0;
        double sum;
            for (int i = 0; i < trainingSessionData.size(); i++) {
                sum = trainingSessionData.get(i).getMovementSpeed();
                avg = sum/trainingSessionData.size();
            }
            return avg;
        }
    }
