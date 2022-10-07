package guides.hazelcast.springboot;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
public class CommandController {
    
    private final String RUNNING = "Running...";
    
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String,String> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    @PostMapping("/put")
    public CommandResponse put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        retrieveMap().put(key, RUNNING);
        IExecutorService taskexecutor 
                = hazelcastInstance.getExecutorService( "exec" );
        taskexecutor.execute( new DataPrepTask(key,value) );
        return new CommandResponse(RUNNING);
    }

    @GetMapping("/get")
    public CommandResponse get(@RequestParam(value = "key") String key) {
        String value = retrieveMap().get(key);       
        return new CommandResponse(value);
    }

    @GetMapping("/showall")
    public List<CommandResponse> showall() {                
        List<CommandResponse> list 
            = new ArrayList<> ();    
        retrieveMap().forEach((k,v)->{
            list.add(new CommandResponse(k+"="+v));
        });
        return list;
    }
}
