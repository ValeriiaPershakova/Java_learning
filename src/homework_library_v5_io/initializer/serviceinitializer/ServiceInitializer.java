package homework_library_v5_io.initializer.serviceinitializer;

import homework_library_v5_io.author.repo.AuthorRepo;
import homework_library_v5_io.author.repo.AuthorRepoArrayImpl;
import homework_library_v5_io.author.repo.AuthorRepoCollectionImpl;
import homework_library_v5_io.author.service.AuthorServiceImpl;
import homework_library_v5_io.book.repo.BookRepo;
import homework_library_v5_io.book.repo.BookRepoArrayImpl;
import homework_library_v5_io.book.repo.BookRepoCollectionImpl;
import homework_library_v5_io.book.service.BookServiceImpl;
import homework_library_v5_io.storage.StorageType;

public class ServiceInitializer {
    private BookRepo bookRepo;
    private AuthorRepo authorRepo;

    public ServicesHolder initServices(StorageType storageType) {
        initRepos(storageType);
        return new ServicesHolder(
                new BookServiceImpl(authorRepo, bookRepo),
                new AuthorServiceImpl(authorRepo, bookRepo)
        );
    }

    private void initRepos(StorageType storageType) {
        switch (storageType) {
            case ARRAY: {
                bookRepo = new BookRepoArrayImpl();
                authorRepo = new AuthorRepoArrayImpl();
                break;
            }

            case COLLECTION: {
                bookRepo = new BookRepoCollectionImpl();
                authorRepo = new AuthorRepoCollectionImpl();
                break;
            }
        }
    }
}
