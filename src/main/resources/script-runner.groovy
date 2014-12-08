import com.branegy.scripting.api.ScriptResult;
import com.branegy.scripting.api.impl.ScriptFactoryManager
import com.branegy.tools.impl.provider.Writer2ErrorLog;
import com.branegy.scripting.api.ScriptResult
import com.branegy.scripting.api.ScriptApiException
import com.branegy.tools.impl.provider.Writer2ErrorLog

import java.io.IOException;
import java.io.Writer;

def groovyPrint(String s){
    print s;
};

try{
    new ScriptFactoryManager()
        .inlineScript("groovy".equalsIgnoreCase(p_lang)?"groovy":"js",p_source)
        .setErrorWriter(new Writer(){
            public void write(char[] cbuf, int off, int len) throws IOException{
                logger.error(new String(cbuf,off,len));
            };
            public void flush() throws IOException{};
            public void close() throws IOException{};
        })
        .setWriter(new Writer(){
            public void write(char[] cbuf, int off, int len) throws IOException{
                groovyPrint(new String(cbuf,off,len)); 
            };
            public void flush() throws IOException{};
            public void close() throws IOException{};
        })
        .setBinding("logger", logger)
        .setBinding("dbm", dbm)
        .setBinding("parameters", parameters)
        .setBinding("tool_linker", tool_linker)
        .eval();
} catch (ScriptApiException e){
    throw e.getCause().getCause();
}