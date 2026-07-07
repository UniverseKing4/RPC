import os

def rename_content(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
    except UnicodeDecodeError:
        return # Skip binary files

    new_content = content.replace('com.my.kizzy', 'com.my.rpc')
    new_content = new_content.replace('Kizzy', 'Rpc')
    new_content = new_content.replace('kizzy', 'rpc')
    new_content = new_content.replace('KIZZY', 'RPC')
    
    if new_content != content:
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(new_content)

def rename_paths_and_contents(root_dir):
    for root, dirs, files in os.walk(root_dir, topdown=False):
        # We only skip actual build directories, not build-logic
        if '.git' in root or '/.gradle' in root or '/build/' in root or root.endswith('/build'):
            continue

        for file_name in files:
            file_path = os.path.join(root, file_name)
            if file_name.endswith(('.png', '.webp', '.jpg', '.jpeg', '.jar', '.zip')):
                continue

            rename_content(file_path)

            if 'Kizzy' in file_name or 'kizzy' in file_name:
                new_name = file_name.replace('Kizzy', 'Rpc').replace('kizzy', 'rpc')
                new_path = os.path.join(root, new_name)
                os.rename(file_path, new_path)

        for dir_name in dirs:
            if dir_name == '.git' or dir_name == 'build' or dir_name == '.gradle':
                continue
            
            if 'Kizzy' in dir_name or 'kizzy' in dir_name:
                old_dir_path = os.path.join(root, dir_name)
                new_name = dir_name.replace('Kizzy', 'Rpc').replace('kizzy', 'rpc')
                new_dir_path = os.path.join(root, new_name)
                os.rename(old_dir_path, new_dir_path)

if __name__ == '__main__':
    rename_paths_and_contents('/workspaces/codespaces-blank/app/RPC/build-logic')
    print("Done renaming build-logic!")
