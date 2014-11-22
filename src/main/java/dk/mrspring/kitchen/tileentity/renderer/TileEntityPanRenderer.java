package dk.mrspring.kitchen.tileentity.renderer;

import dk.mrspring.kitchen.ModInfo;
import dk.mrspring.kitchen.model.ModelPan;
import dk.mrspring.kitchen.pan.IIngredientRenderingHandler;
import dk.mrspring.kitchen.pan.Ingredient;
import dk.mrspring.kitchen.tileentity.TileEntityPan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by MrSpring on 24-10-2014 for TheKitchenMod.
 */
public class TileEntityPanRenderer extends TileEntitySpecialRenderer
{
    protected ModelPan model;
    protected ResourceLocation textureLocation;

    public TileEntityPanRenderer()
    {
        this.model = new ModelPan();
        this.textureLocation = new ResourceLocation(ModInfo.toTexture("textures/models/pan.png"));
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        GL11.glPushMatrix();

        int metadata;
        metadata = tileEntity.getBlockMetadata();

        GL11.glRotatef(metadata * (90), 0F, 1F, 0F);

        GL11.glPushMatrix();

        float pixel = 0.0625F;
        switch (metadata)
        {
            case 0:
                GL11.glTranslatef(2 * pixel, 0F, -(4 * pixel));
                break;
            case 1:
                GL11.glTranslatef(4 * pixel, 0F, -(5 * pixel));
                break;
            case 2:
                GL11.glTranslatef(5 * pixel, 0F, -(4 * pixel));
                break;
            case 3:
                GL11.glTranslatef(4 * pixel, 0F, -(2 * pixel));
                break;
        }


        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureLocation);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        TileEntityPan pan = (TileEntityPan) tileEntity;

        Ingredient ingredient = pan.getIngredient();
        if (ingredient != null)
            if (ingredient != Ingredient.getIngredient("empty"))
            {
                IIngredientRenderingHandler renderingHandler = ingredient.getRenderingHandler();
                ModelBase ingredientModel = renderingHandler.getModel(pan.getCookTime(), ingredient);
                if (renderingHandler.useColorModifier(pan.getCookTime(), ingredient))
                {
                    float[] colors = renderingHandler.getColorModifier(pan.getCookTime(), ingredient);
                    GL11.glColor4f(colors[0] / 255, colors[1] / 255, colors[2] / 255, 1F);
                }
                if (renderingHandler.scaleOnPan(pan.getCookTime(), ingredient))
                {
                    GL11.glTranslatef(0.0F, 0.835F, 0.0F);
                    GL11.glScalef(0.4F, 0.4F, 0.4F);
                }
                ingredientModel.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            }

        GL11.glPopMatrix();

        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
