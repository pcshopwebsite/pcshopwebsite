import { CommonModule } from "@angular/common";
import { Component, OnInit, ViewChild } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { RouterModule } from "@angular/router";
import { FilePondOptions } from "filepond";
import { FilePondComponent, FilePondModule } from "ngx-filepond";

@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.scss'],
    standalone: true,
    imports: [
      CommonModule,
      MatIconModule,
      MatButtonModule,
      RouterModule,
      FormsModule,
      FilePondModule
    ]
  })
  export class FileUploadComponent implements OnInit {
    @ViewChild('myPond')
    myPond!: FilePondComponent;
    
    public constructor() { }
    ngOnInit(): void {
    }

    pondOptions: FilePondOptions = {
      allowMultiple: true,
      labelIdle: 'Drop files here...',
      server: {
        url: "http://localhost:8080/api/upload",
        process: {
            url: "/process",
            // ondata: (formData) => {
            //     return this.handleOnData(formData);
            // }
        }
      },
      chunkUploads: true,
      chunkForce: true,
      chunkSize: 2 * 1024 * 1024,
      instantUpload: false
    }
  
    pondFiles: FilePondOptions["files"] = []
  
    pondHandleInit() {
      console.log('FilePond has initialised', this.myPond);
    }
  
    pondHandleAddFile(event: any) {
      console.log('A file was added', event);
      const expectedOffsets: number[] = this.calculateOffsets(
            event.file.fileSize,
            2048 * 1024
      );
      event.file.setMetadata('expectedOffsets', expectedOffsets);
      console.log(event.file.getMetadata());
      
    }
  
    pondHandleActivateFile(event: any) {
      console.log('A file was activated', event)
    }

    handleOnData(formData: FormData): FormData {
        return formData;
    }

    private calculateOffsets(totalSize: number, chunkSize: number): number[] {
        const numChunks = Math.ceil(totalSize / chunkSize);
        const offsets = [];
    
        for (let i = 0; i < numChunks; i++) {
            const offset = i * chunkSize;
            offsets.push(offset);
        }
    
        return offsets;
    }
  }
