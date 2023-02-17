import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {

    public TrainingSessionWithData getTraningSessionDataByTrainingId(Integer trainingId) {

        String url = "jdbc:mysql://localhost:3306/mydb2";

        String user = "root";

        String password = "qwerty12345678910#";

        TrainingSessionWithData trainingSessionWithData = null;

        try {

            TrainingSessionData trainingSessionData;

            List<TrainingSessionData> trainingSessionDataList = new ArrayList<>();

            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM training_session ts inner join training_session_data tsd on ts.ID_TRAINING_SESSION = tsd.TRAINING_SESSION where ts.ID_TRAINING_SESSION = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, trainingId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String value = resultSet.getString("SESSION_TYPE");

                TrainingSessionWithData.Session session = TrainingSessionWithData.Session.valueOf(value);

                trainingSessionData = new TrainingSessionData(
                        resultSet.getLong("ID_TRAINING_SESSION_DATA"),
                        resultSet.getDouble("LATITUDE"),
                        resultSet.getDouble("LONGITUDE"),
                        resultSet.getDouble("MOVEMENT_SPEED"),
                        resultSet.getDouble("HEART_RATE"),
                        resultSet.getInt("TRAINING_SESSION"));
                trainingSessionDataList.add(trainingSessionData);

                trainingSessionWithData = new TrainingSessionWithData(
                        trainingSessionDataList,
                        resultSet.getInt("ID_TRAINING_SESSION"),
                        resultSet.getDate("START_TIME"),
                        resultSet.getDate("END_TIME"),
                        session,
                        resultSet.getString("USER_USERNAME"));
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainingSessionWithData;
    }
}
