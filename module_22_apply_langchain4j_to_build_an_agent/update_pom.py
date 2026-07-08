import os
import xml.etree.ElementTree as ET
import re

dirs = ['demo', 'exercise/starter', 'exercise/solution']

for d in dirs:
    pom_path = os.path.join(d, 'pom.xml')
    if not os.path.exists(pom_path):
        continue
    
    with open(pom_path, 'r') as f:
        content = f.read()
    
    # Remove spring-ai dependencies and bom
    content = re.sub(r'<spring-ai\.version>.*?</spring-ai\.version>', r'<langchain4j.version>0.31.0</langchain4j.version>', content)
    
    # Replace the dependency
    content = re.sub(
        r'<dependency>\s*<groupId>org\.springframework\.ai</groupId>\s*<artifactId>spring-ai-starter-model-openai</artifactId>\s*</dependency>',
        r'<dependency>\n            <groupId>dev.langchain4j</groupId>\n            <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>\n            <version>${langchain4j.version}</version>\n        </dependency>',
        content
    )
    
    # Remove dependencyManagement entirely as we don't need the bom for this simple setup (or replace with langchain4j BOM)
    content = re.sub(r'<dependencyManagement>.*?</dependencyManagement>', '', content, flags=re.DOTALL)
    
    with open(pom_path, 'w') as f:
        f.write(content)
