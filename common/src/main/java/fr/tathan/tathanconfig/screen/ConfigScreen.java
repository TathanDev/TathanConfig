package fr.tathan.tathanconfig.screen;

import com.electronwill.nightconfig.core.Config;
import fr.tathan.tathanconfig.TathanConfig;
import fr.tathan.tathanconfig.config.BaseConfig;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.SpacerElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

public class ConfigScreen extends Screen {

    private final BaseConfig config;
    private final Screen parent;
    private int yOffset = 0;
    private GridLayout gridLayout = new GridLayout();

    public ConfigScreen(Screen parent, Component name, BaseConfig config) {
        super(name);
        this.config = config;
        this.parent = parent;
    }

    @Override
    protected void init() {

        gridLayout.defaultCellSetting().paddingHorizontal(5).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(2);

        ConfigScreen.this.config.config.valueMap().forEach((key, entry) -> {

            ConfigScreen.this.addTypeWidgets(key, entry, null, rowHelper, key);
            rowHelper.addChild(SpacerElement.height(5), 2);
        });


        Button doneButton = Button.builder(CommonComponents.GUI_DONE, (button) -> ConfigScreen.this.onClose()).width(200).build();

        rowHelper.addChild(doneButton, 2, rowHelper.newCellSettings().paddingTop(10));

        gridLayout.arrangeElements();
        gridLayout.visitWidgets(ConfigScreen.this::addRenderableWidget);

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {

        if (this.yOffset > 0) {
            this.yOffset = 0;
        }

        this.yOffset += scrollY * 10;

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    public void addTypeWidgets(String key, Object entry, @Nullable String description, GridLayout.RowHelper rowHelper, String entryName) {

        if(description == null) {
            description = "No Description";
        }

        if(entry.getClass() == Boolean.class) {

            StringWidget widget = new StringWidget(Component.literal(key), this.font);
            rowHelper.addChild(widget);

            Checkbox checkbox = Checkbox.builder(Component.literal(key), this.font)
                    .selected((Boolean) entry)
                    .tooltip(Tooltip.create(Component.literal(description),null))
                    .onValueChange((checkbox1, aBoolean) ->  {
                        this.config.config.set(entryName, aBoolean);
                        this.config.config.save();
                    })
                    .build();
            rowHelper.addChild(checkbox);

        } else if (entry.getClass() == String.class ) {
            addKeyName(key, rowHelper);

            EditBox button = new EditBox(this.font, 150, 20, Component.literal(entry.toString()));
            button.setTooltip(Tooltip.create(Component.literal(description),null));
            button.setValue(entry.toString());
            button.setResponder((string) ->  {
                this.config.config.set(entryName, string);
                this.config.config.save();
            });
            rowHelper.addChild(button);
        } else if (entry instanceof Config descriptiveEntry) {
            this.addTypeWidgets(key, descriptiveEntry.get("value"), descriptiveEntry.get("description"), rowHelper, entryName);
        } else {
            addKeyName(key, rowHelper);

            Button button = Button.builder(Component.literal("Open File"), (button1) -> {
                Path path = Path.of(this.config.getPath());
                Util.getPlatform().openUri(path.toUri());
            }).tooltip(Tooltip.create(Component.literal("This config type is not supported. Use the manual config"), null)).build();
            rowHelper.addChild(button);
        }
    }

    @Override
    public void onClose() {
        this.playToast(Component.literal("Config Saved"), Component.literal("The config for " + this.config.getName() + " has been saved"));

        this.minecraft.setScreen(this.parent);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == 340) {
            this.yOffset = 0;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, -1);
        FrameLayout.alignInRectangle(gridLayout, 0, this.height / 6 + 10 + this.yOffset, this.width, this.height , 0.5F, 0.0F);

    }

    public void addKeyName(String key, GridLayout.RowHelper rowHelper) {
        StringWidget widget = new StringWidget(Component.translatable("config." + config.getName() + "." + key), this.font);
        widget.setHeight(widget.getHeight() + 15);
        rowHelper.addChild(widget);
    }

    public void playToast(Component title, Component description) {
        this.minecraft.getToasts().addToast(new SystemToast(
                SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                title,
                description
        ));
    }

}
