/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guides.hazelcast.springboot;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author GIANGR40
 */

public class DataPrepTask implements Runnable, Serializable, HazelcastInstanceAware {

    private static final long serialVersionUID = 1L;
    private transient HazelcastInstance hazelcastInstance;
    private final String key;
    private final String value;

    private ConcurrentMap<String,String> retrieveMap() {
        ConcurrentMap<String,String> map =
                hazelcastInstance.getMap("map");        
        return map;
    }
    
    public DataPrepTask(String key,String value) {        
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }        
        retrieveMap().replace(key, value);        
        System.out.println("### Task finished");
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hi) {
        this.hazelcastInstance = hi;
    }

}
