package springmvc2.alltest.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import springmvc2.alltest.item.domain.Item;
import springmvc2.alltest.item.domain.ItemRepository;
import springmvc2.alltest.item.domain.UploadFile;
import springmvc2.alltest.item.file.FileStore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * date           : 2024-11-06
 * created by     : 임경재 
 * description    :
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping()
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "items/items";
    }

    @GetMapping("/add")
    public String addItem(@ModelAttribute("item") ItemForm item) {

        return "items/addForm";
    }

    @PostMapping("/add")
    public String saveItem(@Validated @ModelAttribute("item") ItemForm item, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.error("====================================================");
            log.error("error : [{}]", bindingResult);
            return "items/addForm";
        }

        Item saveItem = new Item();
        saveItem.setItemName(item.getItemName());
        saveItem.setPrice(item.getPrice());
        saveItem.setQuantity(item.getQuantity());
//        saveItem.setAttachFile(item.getAttachFile());
//        saveItem.setImageFiles(item.getImageFiles());
        UploadFile uploadFile = fileStore.storeFile(item.getAttachFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(item.getImageFiles());

        saveItem.setAttachFile(uploadFile);
        saveItem.setImageFiles(uploadFiles);
        itemRepository.saveItem(saveItem);

        redirectAttributes.addAttribute("status", true);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(fileName));
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> getAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String uploadFileName = item.getAttachFile().getUploadFileName();
        String storeFileName = item.getAttachFile().getStoreFileName();

        String fullPath = fileStore.getFullPath(storeFileName);
        UrlResource urlResource = new UrlResource("file:" + fullPath);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, "UTF-8");
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }


}
