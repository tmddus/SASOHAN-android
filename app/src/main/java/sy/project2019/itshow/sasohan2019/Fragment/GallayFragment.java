package sy.project2019.itshow.sasohan2019.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.R;

public class GallayFragment extends Fragment {
    final int REQ_CODE_SELECT_IMAGE = 100;
    byte[] imageByte;
    View view;
    private ImageButton add_photo;
    private ArrayList<Bitmap> imgarray = new ArrayList<>();
    DisplayMetrics mMetrics;
    DBHelper db;

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    Uri photoUri;

    ArrayList<byte[]> imagesArr;

    public GallayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sojunghae_1, container, false);
        db = new DBHelper(getActivity(), DBHelper.tableName, null, 1);




        imagesArr = db.getImage();
        Log.e("gjirjiowf", imagesArr.size() + "");
        
        if(imagesArr.size() != 0){
            for(int i=0; i<imagesArr.size(); i++){

                imgarray.add(byteArrayToBitmap(imagesArr.get(i)));
            }
        }

//        imgarray.add(R.drawable.c);
//        imgarray.add(R.drawable.e);
//        imgarray.add(R.drawable.q);
//        imgarray.add(R.drawable.e);
//        imgarray.add(R.drawable.j);
//        imgarray.add(R.drawable.q);
//        imgarray.add(R.drawable.c);
//        imgarray.add(R.drawable.e);

        // 커스텀 아답타 생성
        ImageAdapter adapter = new ImageAdapter(getContext());    // 데이터

        GridView gv = view.findViewById(R.id.gridview);
        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        //갤러리 열기
        add_photo = view.findViewById(R.id.btn_addphoto);
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlbum();
            }
        });

        return view;
    }


    private void goToAlbum() { // 앨범으로 이동하는 메소드

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

        startActivityForResult(intent, PICK_FROM_ALBUM); // 갤러리에서 사진 가져옴
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리에서의 처리
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) { // 갤러리에서 가져오는 이미지가 없을 때 삭제
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }
            return;
        }  // 갤러리에서 가져오기 실패 (취소)

        if (requestCode == PICK_FROM_ALBUM) {
            photoUri = data.getData();
            String filePath = getPath(photoUri); // 이걸로 file 처리
            String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1); // 사진 타입
            Bitmap image_bitmap;

            try {

                if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") ||
                        file_extn.equals("gif") || file_extn.equals("png")) {
                    image_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    byte[] image_byte = bitmapToByteArr(image_bitmap);
                    db.imageInsert(image_byte);

                    Toast.makeText(getActivity(), "이미지 저장 완료", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getActivity(), "지원하지 않는 형식의 파일입니다", Toast.LENGTH_SHORT).show();
                    return;
                }

            }catch(Exception e){
                e.getStackTrace();
            }

        }
    } // onActivityResult

    public String getPath(Uri uri) { // 이미지 파일 경로 얻는 메서드
        int column_index; // 갤러리에서 사진의 좌표
        String imagePath; // 사진의 경로

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);

        Log.e("imagepath", imagePath);

        return imagePath;
    }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
////        Toast.makeText(getActivity().getBaseContext(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();
//
//        if (requestCode == REQ_CODE_SELECT_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                try {
//                    //Uri에서 이미지 이름을 얻어온다.
//                    //String name_Str = getImageNameToUri(data.getData());
//
//                    //이미지 데이터를 비트맵으로 받아온다.
//
//                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
//                    ImageView image = (ImageView) view.findViewById(R.id.imageView1);
//
//                    imageByte = bitmapToByteArr(image_bitmap);
//                    db.imageInsert(imageByte);
//
//                    //배치해놓은 ImageView에 set
//                    image.setImageBitmap(image_bitmap);
//
//                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
//
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }else{
//            Toast.makeText(getActivity(), "오류"+resultCode, Toast.LENGTH_SHORT).show();
//        }
//    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imgarray.size();
        }

        public Object getItem(int position) {
            return imgarray.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

//            int rowWidth = (mMetrics.widthPixels) / 3;

            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(1, 1, 1, 1);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageBitmap(imgarray.get(position));
            return imageView;
        }
    }


    public byte[] bitmapToByteArr(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;

    }
}