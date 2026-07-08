import os

solution_agent_code = """package com.ecommerce.agent;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.SystemMessage;

@AiService
public interface TravelAssistant {
    @SystemMessage("You are an expert travel assistant. You have access to tools to check flights and weather.")
    String chat(String userMessage);
}
"""

solution_controller_code = """package com.ecommerce.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    private final TravelAssistant travelAssistant;

    public AgentController(TravelAssistant travelAssistant) {
        this.travelAssistant = travelAssistant;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        return travelAssistant.chat(message);
    }
}
"""

solution_tools_code = """package com.ecommerce.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class Tools {

    @Tool("Check flight availability from source to destination")
    public String checkFlight(String source, String destination) {
        return "Flight available for " + source + " to " + destination;
    }

    @Tool("Get current weather for a city")
    public String getWeather(String city) {
        return "Sunny in " + city;
    }
}
"""

starter_agent_code = """package com.ecommerce.agent;

// TODO (Step 1): Import AiService and SystemMessage from langchain4j
// TODO (Step 2): Annotate this interface with @AiService
public interface TravelAssistant {
    // TODO (Step 3): Add @SystemMessage defining the persona
    String chat(String userMessage);
}
"""

starter_controller_code = """package com.ecommerce.agent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    
    // TODO (Step 4): Inject TravelAssistant

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        // TODO (Step 5): Call travelAssistant.chat(message)
        return "";
    }
}
"""

starter_tools_code = """package com.ecommerce.agent;

import org.springframework.stereotype.Component;

@Component
public class Tools {
    // TODO (Step 6): Annotate with @Tool and provide a description
    public String checkFlight(String source, String destination) {
        return "Flight available for " + source + " to " + destination;
    }

    // TODO (Step 7): Annotate with @Tool and provide a description
    public String getWeather(String city) {
        return "Sunny in " + city;
    }
}
"""

def write_files(base_dir, is_starter=False):
    src_dir = os.path.join(base_dir, 'src', 'main', 'java', 'com', 'ecommerce', 'agent')
    os.makedirs(src_dir, exist_ok=True)
    
    with open(os.path.join(src_dir, 'TravelAssistant.java'), 'w') as f:
        f.write(starter_agent_code if is_starter else solution_agent_code)
        
    with open(os.path.join(src_dir, 'AgentController.java'), 'w') as f:
        f.write(starter_controller_code if is_starter else solution_controller_code)
        
    with open(os.path.join(src_dir, 'Tools.java'), 'w') as f:
        f.write(starter_tools_code if is_starter else solution_tools_code)

write_files('exercise/starter', True)
write_files('exercise/solution', False)
write_files('demo', False) # Demo can just be the solution
