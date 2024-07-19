import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;

@SuppressWarnings("CallToPrintStackTrace")
public class ReadKafkaFromFlink {
    public static void main(String[] args) {
        try {
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

            KafkaSource<String> myKafkaSource = KafkaSource.<String>builder()
                    .setBootstrapServers("localhost:9092")
                    .setTopics("hello-world-input")
                    .setStartingOffsets(OffsetsInitializer.latest())
                    .setValueOnlyDeserializer(new SimpleStringSchema())
                    .build();

            DataStream<String> myKafkaStream = env
                    .fromSource(myKafkaSource, WatermarkStrategy.noWatermarks(), "my_source_name");

            myKafkaStream.print();

            env.execute("MyKafkaJob");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
