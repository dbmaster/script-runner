import io.dbmaster.testng.BaseToolTestNGCase;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test

import com.branegy.tools.api.ExportType;

public class ScriptRunnerIT extends BaseToolTestNGCase {

    @Test
    public void test() {
        def parameters = [ "p_lang":"Groovy", "p_source":"println \"ok\"" ]
        tools.toolExecutor("script-runner", parameters).execute()
    }
}
