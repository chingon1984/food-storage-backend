//package com.chingon.FoodStorageApp.service;
//
//import com.chingon.FoodStorageApp.inventory.storage.entity.Storage;
//import com.chingon.FoodStorageApp.inventory.storage.mapper.StorageMapper;
//import com.chingon.FoodStorageApp.inventory.storage.repository.StorageRepository;
//import com.chingon.FoodStorageApp.inventory.storage.service.StorageService;
//import com.chingon.FoodStorageApp.user.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class StorageServiceTest {
//
//    @Mock
//    private StorageRepository storageRepository;
//
//    @Mock
//    private StorageMapper storageMapper;
//
//    @InjectMocks
//    private StorageService storageService;
//
//    private User testUser;
//    private Storage testStorage;
//
//    @BeforeEach
//    void setUp() {
//        // Erstelle einen Test-User
//        testUser = new User();
//        testUser.setId(1L);
//        testUser.setName("testuser");
//        testUser.setEmail("test@example.com");
//        testUser.setPasswordHash("hashedpassword");
//        testUser.setVerified(true);
//
//        // Erstelle einen Test-Storage
//        testStorage = new Storage();
//        testStorage.setId(1L);
//        testStorage.setName("Test Storage");
//        testStorage.setDescription("A test storage");
//        testStorage.setUser(testUser);
//        testStorage.setArchived(false);
//    }
//
//    @Test
//    void getAllStoragesForUser_shouldReturnStoragesForUser() {
//        // Arrange
//        StorageResponse expectedResponse = new StorageResponse(1L, "Test Storage", "A test storage", false);
//
//        when(storageRepository.findByUserIdAndArchivedFalse(testUser.getId()))
//                .thenReturn(List.of(testStorage));
//        when(storageMapper.toResponse(testStorage)).thenReturn(expectedResponse);
//
//        // Act
//        List<StorageResponse> storages = storageService.getAllStoragesForUser(testUser.getId());
//
//        // Assert
//        assertNotNull(storages);
//        assertEquals(1, storages.size());
//        assertEquals("Test Storage", storages.get(0).name());
//        assertEquals("A test storage", storages.get(0).description());
//        assertFalse(storages.get(0).archived());
//
//        verify(storageRepository, times(1)).findByUserIdAndArchivedFalse(testUser.getId());
//        verify(storageMapper, times(1)).toResponse(testStorage);
//    }
//
//    @Test
//    void getAllStoragesForUser_shouldReturnEmptyListForNonExistentUser() {
//        // Arrange
//        when(storageRepository.findByUserIdAndArchivedFalse(999L))
//                .thenReturn(List.of());
//
//        // Act
//        List<StorageResponse> storages = storageService.getAllStoragesForUser(999L);
//
//        // Assert
//        assertNotNull(storages);
//        assertTrue(storages.isEmpty());
//
//        verify(storageRepository, times(1)).findByUserIdAndArchivedFalse(999L);
//    }
//
//    @Test
//    void getStorageById_shouldReturnStorageResponse() {
//        // Arrange
//        StorageResponse expectedResponse = new StorageResponse(1L, "Test Storage", "A test storage", false);
//
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId()))
//                .thenReturn(Optional.of(testStorage));
//        when(storageMapper.toResponse(testStorage)).thenReturn(expectedResponse);
//
//        // Act
//        StorageResponse storage = storageService.getStorageResponseById(testStorage.getId(), testUser.getId());
//
//        // Assert
//        assertNotNull(storage);
//        assertEquals(testStorage.getId(), storage.id());
//        assertEquals("Test Storage", storage.name());
//        assertEquals("A test storage", storage.description());
//        assertFalse(storage.archived());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId());
//        verify(storageMapper, times(1)).toResponse(testStorage);
//    }
//
//    @Test
//    void getStorageById_shouldThrowExceptionForNonExistentStorageResponse() {
//        // Arrange
//        final long id = 999L;
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(999L, testUser.getId()))
//                .thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> storageService.getStorageResponseById(id, testUser.getId()));
//        assertEquals("Storage with ID " + id + " not found.", exception.getMessage());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(999L, testUser.getId());
//    }
//
//    @Test
//    void getStorageResponseById_shouldThrowExceptionForWrongUser() {
//        // Arrange
//        final long id = 999L;
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(testStorage.getId(), id))
//                .thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> storageService.getStorageResponseById(testStorage.getId(), id));
//        assertEquals("Storage with ID " + testStorage.getId() + " not found.", exception.getMessage());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(testStorage.getId(), 999L);
//    }
//
//    @Test
//    void createStorage_shouldCreateNewStorage() {
//        // Arrange
//        Storage newStorage = new Storage();
//        newStorage.setId(2L);
//        newStorage.setName("New Storage");
//        newStorage.setDescription("New description");
//        newStorage.setUser(testUser);
//        newStorage.setArchived(false);
//
//        StorageResponse expectedResponse = new StorageResponse(2L, "New Storage", "New description", false);
//
//        when(storageRepository.save(any(Storage.class))).thenReturn(newStorage);
//        when(storageMapper.toResponse(newStorage)).thenReturn(expectedResponse);
//
//        // Act
//        StorageResponse createdStorage = storageService.createStorage(
//                testUser.getId(), "New Storage", "New description");
//
//        // Assert
//        assertNotNull(createdStorage);
//        assertEquals(2L, createdStorage.id());
//        assertEquals("New Storage", createdStorage.name());
//        assertEquals("New description", createdStorage.description());
//        assertFalse(createdStorage.archived());
//
//        verify(storageRepository, times(1)).save(any(Storage.class));
//        verify(storageMapper, times(1)).toResponse(newStorage);
//    }
//
//    @Test
//    void updateStorage_shouldUpdateExistingStorage() {
//        // Arrange
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId()))
//                .thenReturn(Optional.of(testStorage));
//
//        Storage updatedStorage = new Storage();
//        updatedStorage.setId(testStorage.getId());
//        updatedStorage.setName("Updated Storage");
//        updatedStorage.setDescription("Updated description");
//        updatedStorage.setUser(testUser);
//        updatedStorage.setArchived(false);
//
//        StorageResponse expectedResponse = new StorageResponse(testStorage.getId(), "Updated Storage", "Updated description", false);
//
//        when(storageRepository.save(any(Storage.class))).thenReturn(updatedStorage);
//        when(storageMapper.toResponse(updatedStorage)).thenReturn(expectedResponse);
//
//        // Act
//        StorageResponse result = storageService.updateStorage(
//                testStorage.getId(), testUser.getId(), "Updated Storage", "Updated description");
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(testStorage.getId(), result.id());
//        assertEquals("Updated Storage", result.name());
//        assertEquals("Updated description", result.description());
//        assertFalse(result.archived());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId());
//        verify(storageRepository, times(1)).save(any(Storage.class));
//        verify(storageMapper, times(1)).toResponse(updatedStorage);
//    }
//
//    @Test
//    void updateStorage_shouldThrowExceptionForNonExistentStorage() {
//        // Arrange
//        final long id = 999L;
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(999L, testUser.getId()))
//                .thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> storageService.updateStorage(id, testUser.getId(), "Name", "Description"));
//        assertEquals("Storage with ID " + id + " not found.", exception.getMessage());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(999L, testUser.getId());
//        verify(storageRepository, never()).save(any(Storage.class));
//    }
//
//    @Test
//    void deleteStorage_shouldArchiveStorage() {
//        // Arrange
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId()))
//                .thenReturn(Optional.of(testStorage));
//
//        when(storageRepository.save(any(Storage.class))).thenReturn(testStorage);
//
//        // Act
//        storageService.deleteStorage(testStorage.getId(), testUser.getId());
//
//        // Assert
//        assertTrue(testStorage.getArchived());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId());
//        verify(storageRepository, times(1)).save(any(Storage.class));
//    }
//
//    @Test
//    void deleteStorage_shouldThrowExceptionForNonExistentStorage() {
//        // Arrange
//        final long id = 999L;
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(999L, testUser.getId()))
//                .thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> storageService.deleteStorage(id, testUser.getId()));
//        assertEquals("Storage with ID " + id + " not found.", exception.getMessage());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(999L, testUser.getId());
//        verify(storageRepository, never()).save(any(Storage.class));
//    }
//
//    @Test
//    void createStorage_shouldHandleNullDescription() {
//        // Arrange
//        Storage newStorage = new Storage();
//        newStorage.setId(2L);
//        newStorage.setName("Storage with null description");
//        newStorage.setDescription(null);
//        newStorage.setUser(testUser);
//        newStorage.setArchived(false);
//
//        StorageResponse expectedResponse = new StorageResponse(2L, "Storage with null description", null, false);
//
//        when(storageRepository.save(any(Storage.class))).thenReturn(newStorage);
//        when(storageMapper.toResponse(newStorage)).thenReturn(expectedResponse);
//
//        // Act
//        StorageResponse createdStorage = storageService.createStorage(
//                testUser.getId(), "Storage with null description", null);
//
//        // Assert
//        assertNotNull(createdStorage);
//        assertNull(createdStorage.description());
//
//        verify(storageRepository, times(1)).save(any(Storage.class));
//        verify(storageMapper, times(1)).toResponse(newStorage);
//    }
//
//    @Test
//    void updateStorage_shouldHandleNullDescription() {
//        // Arrange
//        when(storageRepository.findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId()))
//                .thenReturn(Optional.of(testStorage));
//
//        Storage updatedStorage = new Storage();
//        updatedStorage.setId(testStorage.getId());
//        updatedStorage.setName("Updated name");
//        updatedStorage.setDescription(null);
//        updatedStorage.setUser(testUser);
//        updatedStorage.setArchived(false);
//
//        StorageResponse expectedResponse = new StorageResponse(testStorage.getId(), "Updated name", null, false);
//
//        when(storageRepository.save(any(Storage.class))).thenReturn(updatedStorage);
//        when(storageMapper.toResponse(updatedStorage)).thenReturn(expectedResponse);
//
//        // Act
//        StorageResponse result = storageService.updateStorage(
//                testStorage.getId(), testUser.getId(), "Updated name", null);
//
//        // Assert
//        assertNotNull(result);
//        assertNull(result.description());
//
//        verify(storageRepository, times(1)).findByIdAndUserIdAndArchivedFalse(testStorage.getId(), testUser.getId());
//        verify(storageRepository, times(1)).save(any(Storage.class));
//        verify(storageMapper, times(1)).toResponse(updatedStorage);
//    }
//}