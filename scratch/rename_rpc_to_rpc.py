import os
import re

def rename_content(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
    except UnicodeDecodeError:
        return # Skip binary files

    # Replace variations
    new_content = content.replace('com.my.rpc', 'com.my.rpc')
    new_content = new_content.replace('Rpc', 'Rpc')
    new_content = new_content.replace('rpc', 'rpc')
    new_content = new_content.replace('RPC', 'RPC')
    
    if new_content != content:
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(new_content)

def rename_paths_and_contents(root_dir):
    for root, dirs, files in os.walk(root_dir, topdown=False):
        # Skip .git and build directories
        if '.git' in root or '/build' in root or '/.gradle' in root:
            continue

        for file_name in files:
            file_path = os.path.join(root, file_name)
            
            # Skip binary file extensions
            if file_name.endswith(('.png', '.webp', '.jpg', '.jpeg', '.jar', '.zip')):
                continue

            # Rename content first
            rename_content(file_path)

            # Rename file
            if 'Rpc' in file_name or 'rpc' in file_name:
                new_name = file_name.replace('Rpc', 'Rpc').replace('rpc', 'rpc')
                new_path = os.path.join(root, new_name)
                os.rename(file_path, new_path)

        for dir_name in dirs:
            if dir_name == '.git' or dir_name == 'build' or dir_name == '.gradle':
                continue
            
            if 'Rpc' in dir_name or 'rpc' in dir_name:
                old_dir_path = os.path.join(root, dir_name)
                new_name = dir_name.replace('Rpc', 'Rpc').replace('rpc', 'rpc')
                new_dir_path = os.path.join(root, new_name)
                os.rename(old_dir_path, new_dir_path)

if __name__ == '__main__':
    rename_paths_and_contents('/workspaces/codespaces-blank/app/RPC')
    print("Done renaming!")
