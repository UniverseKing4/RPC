from PIL import Image
import os

fg_path = "/home/codespace/.gemini/antigravity-cli/brain/ec361bdb-8988-40d8-8d5d-9f669f68479d/rpc_app_icon_fg_1783441362080.jpg"
bg_path = "/home/codespace/.gemini/antigravity-cli/brain/ec361bdb-8988-40d8-8d5d-9f669f68479d/rpc_app_icon_bg_1783441371273.jpg"

res_dir = "/workspaces/codespaces-blank/app/RPC/app/src/main/res"

fg_img = Image.open(fg_path).convert("RGBA")
bg_img = Image.open(bg_path).convert("RGBA")

# For foreground, let's try to remove a solid color background (DALL-E usually makes it black or checkered).
# Actually, to make it perfectly adaptive, let's just make a circular crop with transparent background.
# Wait, adaptive icon foregrounds should be 108x108 dp, where 72x72 is the safe zone.
# Let's resize to 1024x1024.
fg_img = fg_img.resize((1024, 1024), Image.Resampling.LANCZOS)
bg_img = bg_img.resize((1024, 1024), Image.Resampling.LANCZOS)

# Create a clean foreground mask if possible, or just keep it as is.
# If it has a checkered background, it's hard to remove. I'll just save it as webp.
# But if we just save it as foreground, it will mask the background. Let's just make the background transparent based on the corner pixel?
corner_pixel = fg_img.getpixel((0, 0))
datas = fg_img.getdata()
newData = []
# Threshold for transparency
for item in datas:
    if abs(item[0]-corner_pixel[0]) < 20 and abs(item[1]-corner_pixel[1]) < 20 and abs(item[2]-corner_pixel[2]) < 20:
        newData.append((255, 255, 255, 0))
    else:
        newData.append(item)
fg_img.putdata(newData)

sizes = {
    "mipmap-mdpi": 48,
    "mipmap-hdpi": 72,
    "mipmap-xhdpi": 96,
    "mipmap-xxhdpi": 144,
    "mipmap-xxxhdpi": 192
}

for folder, size in sizes.items():
    folder_path = os.path.join(res_dir, folder)
    os.makedirs(folder_path, exist_ok=True)
    
    # Standard launcher
    fg_scaled = fg_img.resize((size, size), Image.Resampling.LANCZOS)
    bg_scaled = bg_img.resize((size, size), Image.Resampling.LANCZOS)
    
    # Combine for non-adaptive (legacy)
    legacy = Image.alpha_composite(bg_scaled, fg_scaled)
    legacy.save(os.path.join(folder_path, "ic_launcher.webp"))
    legacy.save(os.path.join(folder_path, "ic_launcher_round.webp"))
    
    # Adaptive assets
    ad_size = int(size * 1.5) # 108dp is 1.5x of 72dp
    fg_ad = fg_img.resize((ad_size, ad_size), Image.Resampling.LANCZOS)
    bg_ad = bg_img.resize((ad_size, ad_size), Image.Resampling.LANCZOS)
    
    fg_ad.save(os.path.join(folder_path, "ic_launcher_foreground.webp"))
    bg_ad.save(os.path.join(folder_path, "ic_launcher_background.webp"))

# Remove old XML files
os.system(f"rm -f {res_dir}/drawable/ic_launcher_background.xml")
os.system(f"rm -f {res_dir}/drawable/ic_launcher_foreground.xml")
os.system(f"rm -f {res_dir}/drawable-v24/ic_launcher_foreground.xml")
os.system(f"rm -f {res_dir}/mipmap-anydpi-v26/ic_launcher_monochrome.xml")
