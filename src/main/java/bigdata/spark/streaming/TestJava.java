package bigdata.spark.streaming;

/**
 * Created by Administrator on 2016/11/3.
 */
public class TestJava {
    public static void main(String[] args) {
        /*JavaStreamingContextFactory contextFactory = new JavaStreamingContextFactory() {
            @Override
            public JavaStreamingContext create() {
                JavaStreamingContext jssc = new JavaStreamingContext(...);
                JavaDStream<String> lines = jssc.socketTextStream(...);
                jssc.checkpoint(checkpointDirectory);
                return jssc;
            }
        };

        JavaStreamingContext context = JavaStreamingContext.getOrCreate(checkpointDirectory, contextFactory);
        context.start();
        context.awaitTermination();*/
    }
}
