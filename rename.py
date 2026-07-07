import os
import glob

# Words to replace in content and filenames
replacements = {
    "feature_rpc": "feature_rpc",
    "Rpc": "Rpc",
    "rpc": "rpc",
    "RPC": "RPC",
    "rpc": "rpc",
    "xyz.dead8309.feature_rpc": "xyz.dead8309.feature_rpc",
    "RPC": "RPC",
    "RPC": "RPC",
    "main_rpc": "main_rpc",
    "start_rpc": "start_rpc",
    "enable_rpc": "enable_rpc"
}

def rename_content():
    for root, dirs, files in os.walk('.'):
        if '.git' in root or '/build' in root or '/.gradle' in root or '__pycache__' in root:
            continue
        for file in files:
            if file.endswith(('.kt', '.xml', '.kts', '.java', '.pro', '.properties', '.json', '.md', '.py')):
                filepath = os.path.join(root, file)
                try:
                    with open(filepath, 'r', encoding='utf-8') as f:
                        content = f.read()
                    
                    new_content = content
                    for old, new in replacements.items():
                        new_content = new_content.replace(old, new)
                        
                    # Also generic fallback for 'rpc' if not caught above
                    # Actually wait, let's just do a generic replace of "rpc" to "rpc" case-insensitively?
                    # "Rpc" -> "Rpc", "rpc" -> "rpc", "RPC" -> "RPC"
                    new_content = new_content.replace("Rpc", "Rpc")
                    new_content = new_content.replace("rpc", "rpc")
                    new_content = new_content.replace("RPC", "RPC")

                    if new_content != content:
                        with open(filepath, 'w', encoding='utf-8') as f:
                            f.write(new_content)
                except Exception as e:
                    print(f"Failed to process {filepath}: {e}")

def rename_files_and_dirs():
    # Rename from bottom-up to not break paths
    for root, dirs, files in os.walk('.', topdown=False):
        if '.git' in root or '/build' in root or '/.gradle' in root:
            continue
            
        for name in files + dirs:
            new_name = name
            for old, new in replacements.items():
                new_name = new_name.replace(old, new)
            new_name = new_name.replace("Rpc", "Rpc")
            new_name = new_name.replace("rpc", "rpc")
            new_name = new_name.replace("RPC", "RPC")
            
            if new_name != name:
                old_path = os.path.join(root, name)
                new_path = os.path.join(root, new_name)
                print(f"Renaming {old_path} to {new_path}")
                os.rename(old_path, new_path)

print("Renaming content...")
rename_content()
print("Renaming files and dirs...")
rename_files_and_dirs()
print("Done!")
