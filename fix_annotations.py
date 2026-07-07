import os

replacements = {
    "ExperimentalCoroutinesApi": "ExperimentalCoroutinesApi",
    "ExperimentalMaterial3Api": "ExperimentalMaterial3Api",
    "ExperimentalFoundationApi": "ExperimentalFoundationApi",
    "ExperimentalAnimationApi": "ExperimentalAnimationApi",
    "ExperimentalContracts": "ExperimentalContracts",
    "ExperimentalStdlibApi": "ExperimentalStdlibApi",
    "ExperimentalOptIn": "ExperimentalOptIn",
    "OptIn(Experimental": "OptIn(Experimental",
    "androidx.compose.animation.Experimental": "androidx.compose.animation.Experimental",
    "androidx.compose.foundation.Experimental": "androidx.compose.foundation.Experimental",
    "androidx.compose.material3.Experimental": "androidx.compose.material3.Experimental",
    "kotlinx.coroutines.Experimental": "kotlinx.coroutines.Experimental"
}

for root, dirs, files in os.walk('.'):
    if '.git' in root or '/build' in root or '/.gradle' in root:
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
                
                if new_content != content:
                    with open(filepath, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Fixed {filepath}")
            except Exception as e:
                pass
print("Done fixing annotations!")
