import os
import re

for root, dirs, files in os.walk('.'):
    if '.git' in root or '/build' in root or '/.gradle' in root:
        continue
    for file in files:
        if file.endswith(('.kt', '.xml', '.kts', '.java')):
            filepath = os.path.join(root, file)
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                # Replace RpcPermissionsApi -> ExperimentalPermissionsApi
                new_content = content.replace("RpcPermissionsApi", "ExperimentalPermissionsApi")
                new_content = new_content.replace("com.google.accompanist.permissions.Rpc", "com.google.accompanist.permissions.Experimental")
                
                if new_content != content:
                    with open(filepath, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Fixed {filepath}")
            except Exception as e:
                pass
print("Done fixing annotations 2!")
